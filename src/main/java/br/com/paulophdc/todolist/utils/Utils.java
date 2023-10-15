package br.com.paulophdc.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
	// o contexto desta classe inteira foi alterar as informações passadas no json
	// e manter na tela as demais que nao foram alteradas
	
	public static void copyNonNullProperties(Object source, Object target) {
		BeanUtils.copyProperties(source, target,getNullPropertyNames(source));
	}
	
	
	public static String [] getNullPropertyNames(Object source) {//BeanWrapper é uma interface que fornece
		final BeanWrapper src = new BeanWrapperImpl(source); //uma forma para acessar um Objeto dentro do java e BeanWrapperImpl é a implementação desta interface
		
		PropertyDescriptor[] pds =  src.getPropertyDescriptors();
		
		Set<String> emptyNames = new HashSet<>();
		
		for(PropertyDescriptor pd: pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if(srcValue == null) {
				emptyNames.add(pd.getName());// todas as propriedades nulas vem para ca
			}
		}
		
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);// é preciso converter o conjunto de nomes 
	}// das propriedades para um array de string
}
