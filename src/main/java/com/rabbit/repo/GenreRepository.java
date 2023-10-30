package com.rabbit.repo;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.rabbit.domain.Genre;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface GenreRepository extends PageableRepository<Genre, Long> {

	Genre save(@NonNull @NotBlank String name);

	@Transactional
	default Genre saveWithException(@NonNull @NotBlank String name) {
		save(name);
		throw new DataAccessException("test exception");
	}

	long update(@NonNull @NotNull @Id Long id, @NonNull @NotBlank String name);
}