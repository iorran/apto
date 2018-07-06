package com.lgc.ctps.apto.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lgc.ctps.apto.domain.Function;
import com.lgc.ctps.apto.filter.FunctionFilter;
import com.lgc.ctps.apto.repository.FunctionRepository;

@Service
@Transactional
public class FunctionService {

	@Autowired
	private FunctionRepository functionRepository;

	public Function save(Function function) {
		return functionRepository.save(function);
	}

	public Optional<Function> findById(Long id) {
		return functionRepository.findById(id);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	public Page<Function> search(FunctionFilter filter, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
}
