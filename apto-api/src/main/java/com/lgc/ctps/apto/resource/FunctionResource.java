package com.lgc.ctps.apto.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lgc.ctps.apto.domain.Function;
import com.lgc.ctps.apto.filter.FunctionFilter;
import com.lgc.ctps.apto.service.FunctionService;
import com.lgc.ctps.apto.util.HeaderUtil;
import com.lgc.ctps.apto.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "functions")
@RestController
@RequestMapping("functions")
public class FunctionResource {
	private static final String ENTITY_NAME = "Function";

	@Autowired
	private FunctionService functionService;

	@ApiOperation(value = "Create a new function", httpMethod = "POST", nickname = "createFunction")
	@PostMapping
	public ResponseEntity<Function> createFunction(@Valid @RequestBody Function function) throws URISyntaxException {
		log.debug("REST request to save Function : {}", function);
		if (function.getId() != null) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new function cannot already have an ID"))
					.body(null);
		}
		Function result = functionService.save(function);
		return ResponseEntity.created(new URI("functions/" + result.getId().toString()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.toString())).body(result);
	}

	@ApiOperation(value = "Delete a existing function", httpMethod = "DELETE", nickname = "deleteFunctionById")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteFunctionById(@PathVariable Long id) {
		log.debug("REST request to delete Function : {}", id);
		functionService.delete(id);
		return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()))
				.build();
	}

	@ApiOperation(value = "Recovery function by id", httpMethod = "GET", nickname = "findFunctionById")
	@GetMapping("{id}")
	public ResponseEntity<Function> findFunctionById(@PathVariable Long id) {
		log.debug("REST request to get Function : {}", id);
		Optional<Function> function = functionService.findById(id);
		return ResponseUtil.wrapOrNotFound(function);
	}

	@ApiOperation(value = "View a list of available functions", httpMethod = "GET", nickname = "searchFunction")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "string", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "string", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
					+ "Default sort order is ascending. " + "Multiple sort criteria are supported.") })
	@GetMapping
	public Page<Function> searchFunction(FunctionFilter filter, Pageable pageable) {
		log.debug("REST request to get a list of function");
		return functionService.search(filter, pageable);
	}

	@ApiOperation(value = "Update a existing function", httpMethod = "PUT", nickname = "updateFunction")
	@PutMapping
	public ResponseEntity<Function> updateFunction(@Valid @RequestBody Function function) throws URISyntaxException {
		log.debug("REST request to update Function : {}", function);
		if (function.getId() == null) {
			return createFunction(function);
		}
		Function result = functionService.save(function);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.toString()))
				.body(result);
	}
}
