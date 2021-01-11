
package pe.com.softprogy.access.v1.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
import pe.com.softprogy.access.enumeration.IndexConstraintEnum;
import pe.com.softprogy.access.enumeration.LoginType;
import pe.com.softprogy.access.enumeration.RoleType;
import pe.com.softprogy.access.exception.AccessLogicException;
import pe.com.softprogy.access.v1.service.ClientService;
import pe.com.softprogy.security.config.UserInfo;

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
    @Transactional(propagation = Propagation.SUPPORTS)
    public Response<ClientDTO> add(UserInfo userInfo, AddClientRequest request) throws AccessLogicException
    {
        Response<ClientDTO> response = new Response<ClientDTO>(AccessCodeEnum.OK.status());
        try
        {
            if (LoginType.EMAIL.isCode(request.getLoginType()))
            {

                ClientEntity clientEntity = new ClientEntity();
                clientEntity.setEmail(request.getEmail());
                clientEntity.setName(request.getName());
                clientEntity.setPhone(request.getPhone());
                clientEntity.setFkUser(userInfo.getUserId());
                clientEntity = clientRepo.save(clientEntity);

                UserEntity userEntity = new UserEntity();
                userEntity.setId(clientEntity.getId());
                userEntity.setAccessKey(request.getPassword());
                userEntity = userRepo.save(userEntity);

                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setId(userEntity.getId());
                roleEntity.setName(RoleType.CLIENT.getCode());
                roleEntity = roleRepo.save(roleEntity);

                response.setData(new ClientDTO(clientEntity));
                return response;

            }
            else
            {
                throw new AccessLogicException(AccessCodeEnum.FAIL);
            }
        }
        catch (DataIntegrityViolationException e)
        {
            if (e.getCause() instanceof ConstraintViolationException)
            {
                logger.error(e.getMessage());
                throw new AccessLogicException(IndexConstraintEnum.getCode((ConstraintViolationException)e.getCause()));
            }
            else
            {
                logger.error(e.getMessage(), e);
                throw new AccessLogicException(AccessCodeEnum.FAIL);
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new AccessLogicException(AccessCodeEnum.FAIL);
        }
    }

    @Override
    @Transactional
    public Response<ClientDTO> edit(UserInfo userInfo, EditClientRequest request) throws AccessLogicException
    {
        Response<ClientDTO> response = new Response<ClientDTO>(AccessCodeEnum.OK.status());
        AccessLogicException exception;
        try
        {
            if (LoginType.EMAIL.isCode(request.getLoginType()))
            {

                Optional<ClientEntity> optUserEntity = clientRepo.findByIdAndEmailNotAndFkUser(request.getId(),
                        request.getEmail(), userInfo.getUserId());
                if (optUserEntity.isPresent())
                {
                    ClientEntity clientEntity = optUserEntity.get();
                    clientEntity.setName(request.getName());
                    clientEntity.setPhone(request.getPhone());
                    clientEntity.setEmail(request.getEmail());
                    clientEntity = clientRepo.save(clientEntity);

                    response.setData(new ClientDTO(clientEntity));
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
            Iterable<ClientEntity> users = clientRepo.findAll();
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
    public void delete(UserInfo userInfo, DeleteClientRequest login) throws AccessLogicException
    {
        try
        {
            Optional<ClientEntity> optUserEntity = clientRepo.findByFkUserAndEmail(userInfo.getUserId(),
                    login.getEmail());
            if (optUserEntity.isPresent())
            {
                ClientEntity clientEntity = optUserEntity.get();
                roleRepo.delete(clientEntity.getUser().getRole());
                userRepo.delete(clientEntity.getUser());
                clientRepo.delete(clientEntity);
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new AccessLogicException(AccessCodeEnum.FAIL);
        }
    }

}
