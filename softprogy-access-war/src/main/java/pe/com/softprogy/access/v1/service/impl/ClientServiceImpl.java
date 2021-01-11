
package pe.com.softprogy.access.v1.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.softprogy.access.commons.dto.ClientDTO;
import pe.com.softprogy.access.commons.dto.ClientListDTO;
import pe.com.softprogy.access.commons.entity.ClientEntity;
import pe.com.softprogy.access.commons.entity.RoleEntity;
import pe.com.softprogy.access.commons.entity.UserEntity;
import pe.com.softprogy.access.commons.repository.ClientEntityRepository;
import pe.com.softprogy.access.commons.repository.RoleEntityRepository;
import pe.com.softprogy.access.commons.repository.UserEntityRepository;
import pe.com.softprogy.access.commons.request.AddClientRequest;
import pe.com.softprogy.access.commons.request.DeleteClientRequest;
import pe.com.softprogy.access.commons.request.EditClientRequest;
import pe.com.softprogy.access.commons.response.Response;
import pe.com.softprogy.access.enumeration.AccessCodeEnum;
import pe.com.softprogy.access.enumeration.LoginType;
import pe.com.softprogy.access.enumeration.RoleType;
import pe.com.softprogy.access.exception.AccessLogicException;
import pe.com.softprogy.access.v1.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService
{

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private UserEntityRepository userRepo;

    @Autowired
    private RoleEntityRepository roleRepo;

    @Autowired
    private ClientEntityRepository clientRepo;

    @Override
    @Transactional
    public Response<ClientDTO> add(AddClientRequest request) throws AccessLogicException
    {
        Response<ClientDTO> response = new Response<ClientDTO>(AccessCodeEnum.OK.status());
        AccessLogicException exception;
        try
        {
            if (LoginType.EMAIL.isCode(request.getLoginType()))
            {

                UserEntity userEntity = new UserEntity();
                userEntity.setEmail(request.getEmail());
                userEntity.setAccessKey(request.getPassword());
                userEntity = userRepo.save(userEntity);

                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setId(userEntity.getId());
                roleEntity.setName(RoleType.CLIENT.getCode());
                roleEntity = roleRepo.save(roleEntity);

                ClientEntity clientEntity = new ClientEntity();
                clientEntity.setId(userEntity.getId());
                clientEntity.setName(request.getName());
                clientEntity = clientRepo.save(clientEntity);

                response.setData(new ClientDTO(userEntity));
                return response;

            }
            else
            {
                exception = new AccessLogicException(AccessCodeEnum.FAIL);
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            exception = new AccessLogicException(AccessCodeEnum.FAIL);
        }
        throw exception;
    }

    @Override
    @Transactional
    public Response<ClientDTO> edit(EditClientRequest request) throws AccessLogicException
    {
        Response<ClientDTO> response = new Response<ClientDTO>(AccessCodeEnum.OK.status());
        AccessLogicException exception;
        try
        {
            if (LoginType.EMAIL.isCode(request.getLoginType()))
            {

                Optional<UserEntity> optUserEntity = userRepo.findByIdAndEmailNot(request.getId(), request.getEmail());
                if (optUserEntity.isPresent())
                {
                    UserEntity userEntity = optUserEntity.get();
                    userEntity.setEmail(request.getEmail());

                    ClientEntity clientEntity = userEntity.getClient();
                    clientEntity.setName(request.getName());
                    clientEntity.setPhone(request.getPhone());
                    clientEntity = clientRepo.save(clientEntity);

                    userEntity = userRepo.save(userEntity);
                    response.setData(new ClientDTO(userEntity));
                }
                
                return response;

            }
            else
            {
                exception = new AccessLogicException(AccessCodeEnum.FAIL);
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            exception = new AccessLogicException(AccessCodeEnum.FAIL);
        }
        throw exception;
    }

    @Override
    public Response<ClientListDTO> list() throws AccessLogicException
    {
        try
        {
            Response<ClientListDTO> response = new Response<ClientListDTO>(AccessCodeEnum.OK.status());
            Iterable<UserEntity> users = userRepo.findAll();
            ClientListDTO listDTO = new ClientListDTO();
            listDTO.setClients(
                    StreamSupport.stream(users.spliterator(), false).map(ClientDTO::new).collect(Collectors.toList()));
            response.setData(listDTO);
            return response;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new AccessLogicException(AccessCodeEnum.FAIL);
        }
    }

    @Override
    @Transactional
    public void delete(DeleteClientRequest login) throws AccessLogicException
    {
        try
        {
            Optional<UserEntity> optUserEntity = userRepo.findByEmail(login.getEmail());
            if (optUserEntity.isPresent())
            {
                UserEntity userEntity = optUserEntity.get();
                roleRepo.delete(userEntity.getRole());
                clientRepo.delete(userEntity.getClient());
                userRepo.delete(userEntity);
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new AccessLogicException(AccessCodeEnum.FAIL);
        }
    }

}
