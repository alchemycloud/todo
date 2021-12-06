package com.todo.backend.repository.impl;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.todo.backend.model.QUser;
import com.todo.backend.model.User;
import com.todo.backend.model.enumeration.UserRole;
import com.todo.backend.model.id.UserId;
import com.todo.backend.repository.AbstractSimpleRepositoryImpl;
import com.todo.backend.repository.UserRepository;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractSimpleRepositoryImpl<User, UserId> implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Override
    protected Class<User> getDomainClass() {
        return User.class;
    }

    @Override
    protected EntityPath<User> getEntityPathBase() {
        return QUser.user;
    }

    @Override
    public NumberPath<Long> getEntityIdPathBase() {
        return QUser.user.id;
    }

    @Override
    protected UserId getId(User entity) {
        return entity.getId();
    }

    @Override
    public User create(String firstName, String lastName, UserRole role, String username, String passwordHash) {
        final User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        user.setUsername(username);
        user.setPasswordHash(passwordHash);
        entityManager.persist(user);
        postSave(user);
        return user;
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        LOG.trace(".findByFirstName()");
        final QUser qUser = QUser.user;
        return factory.select(qUser).from(qUser).where(qUser.firstName.eq(firstName)).orderBy(qUser.id.asc().nullsLast()).fetch();
    }

    @Override
    public List<User> findByLastName(String lastName) {
        LOG.trace(".findByLastName()");
        final QUser qUser = QUser.user;
        return factory.select(qUser).from(qUser).where(qUser.lastName.eq(lastName)).orderBy(qUser.id.asc().nullsLast()).fetch();
    }

    @Override
    public List<User> findByRole(UserRole role) {
        LOG.trace(".findByRole()");
        final QUser qUser = QUser.user;
        return factory.select(qUser).from(qUser).where(qUser.role.eq(role)).orderBy(qUser.id.asc().nullsLast()).fetch();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        LOG.trace(".findByUsername()");
        final QUser qUser = QUser.user;
        return Optional.ofNullable(factory.select(qUser).from(qUser).where(qUser.username.eq(username)).orderBy(qUser.id.asc().nullsLast()).fetchOne());
    }

    @Override
    public List<User> findByPasswordHash(String passwordHash) {
        LOG.trace(".findByPasswordHash()");
        final QUser qUser = QUser.user;
        return factory.select(qUser).from(qUser).where(qUser.passwordHash.eq(passwordHash)).orderBy(qUser.id.asc().nullsLast()).fetch();
    }

    @Override
    public List<User> users() {
        LOG.trace(".users()");
        final QUser qUser = QUser.user;
        return factory.select(qUser).from(qUser).orderBy(qUser.id.asc().nullsLast()).fetch();
    }

    @Override
    public List<User> findAllById(Collection<UserId> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        return factory.select(getEntityPathBase()).from(QUser.user).where(QUser.user.id.in(ids.stream().map(UserId::getValue).toList())).fetch();
    }
}
