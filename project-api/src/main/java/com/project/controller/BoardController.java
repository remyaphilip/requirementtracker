package com.project.controller;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Board;
import com.project.model.List;
import com.project.repository.BoardRepository;
import com.project.repository.ListRepository;

@RestController
@RequestMapping("/")
public class BoardController {

	@Autowired
	public ListRepository listRepository;
	@Autowired
	public BoardRepository boardRepository;

	@RequestMapping(path = "board", method = RequestMethod.POST)
	public Board addProjectType(@RequestBody Board projectType) {
		boardRepository.save(projectType);
		return projectType;
	}
	
	@RequestMapping(path = "board/{id}",method = RequestMethod.GET)
	public Board getBoard(@PathVariable("id") Integer id){
		return boardRepository.findOne(id);
	}

	@RequestMapping(path = "board/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public java.util.List<Board> getAllBoards() {
		return boardRepository.findAll();
	}
	


	@RequestMapping(path = "board/{id}/list", method = RequestMethod.POST)
	@Transactional
	public List addList(@PathVariable("id") Integer id, @RequestBody List list) {
		System.out.println(list.getListId());
		list.setBoard(boardRepository.findOne(id));
		listRepository.save(list);
		return list;
	}

	@RequestMapping(path = "list/{id}",method = RequestMethod.GET)
	public List getList(@PathVariable("id") Integer id){
		return listRepository.findOne(id);
	}
	@Transactional
	@RequestMapping(path = "board/{id}/list", method = RequestMethod.GET)
	public Set<List> getAllListPerBoard(@PathVariable("id") Integer id) {
		return boardRepository.findOne(id).getLists();

	}
	
	@RequestMapping(path = "list/{id}",method = RequestMethod.DELETE)
	public void removeList(@PathVariable("id") Integer id){
		listRepository.delete(id);
	}

}
