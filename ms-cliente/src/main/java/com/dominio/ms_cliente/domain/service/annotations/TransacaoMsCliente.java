package com.dominio.ms_cliente.domain.service.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dominio.ms_cliente.domain.enums.TransacaoEnum;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TransacaoMsCliente {
	
	TransacaoEnum transacao();

}
