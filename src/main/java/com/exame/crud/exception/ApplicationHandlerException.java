package com.exame.crud.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exame.crud.exception.error.Error;
import com.exame.crud.exception.error.Fields;
import com.exame.crud.exception.handlers.EntityExistsException;

@RestControllerAdvice
public class ApplicationHandlerException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> general(Exception exception) {
		return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, //
		        Error.builder()//
		                .error("internal-0001")//
		                .message("Algo deu errado")//
		                .detail("Tivemos algum erro na execução, caso o erro persista, entre em contato com o Administrador.")//
		                .build());
	}

	@ExceptionHandler(EntityExistsException.class)
	public ResponseEntity<?> entityExistsException(EntityExistsException e) {
		return createResponse(HttpStatus.UNPROCESSABLE_ENTITY, //
		        Error.builder().error("entity-exists-0001")//
		                .message("Entidade já existe")//
		                .detail("A Entidade enviada para salvar, já existe. Corriga o cadastro e tente novamente.")//
		                .fieldErrors(//
		                        List.of(Fields.builder().name(e.getName()).message(e.getMessage()).build())//
						).build()//
		);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationException(MethodArgumentNotValidException e) {
		final var error = Error.builder()//
		        .error("validation-0001")//
		        .message("Atributos obrigatórios não preenchidos ou incorretos.")//
		        .fieldErrors(//
		                e.getAllErrors().stream().map(err ->
						{//
			                return Fields.builder()//
			                        .name(((FieldError) err).getField())//
			                        .message(err.getDefaultMessage())//
			                        .build();//
		                }).toList())
		        .build();

		return createResponse(HttpStatus.BAD_REQUEST, error);
	}

	private ResponseEntity<?> createResponse(HttpStatus httpStatus, Error error) {
		return ResponseEntity.status(httpStatus).body(error);
	}

}
