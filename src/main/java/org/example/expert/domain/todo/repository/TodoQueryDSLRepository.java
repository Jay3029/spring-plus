package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.entity.QUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TodoQueryDSLRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QTodo qTodo = QTodo.todo;
    private final QUser qUser = QUser.user;

    public Optional<Todo> findByIdWithUser(Long todoId) {
        Todo todo = jpaQueryFactory
                .selectFrom(qTodo)
                .leftJoin(qTodo.user, qUser).fetchJoin()
                .where(qTodo.id.eq(todoId))
                .fetchOne();

        return Optional.ofNullable(todo);
    }
}
