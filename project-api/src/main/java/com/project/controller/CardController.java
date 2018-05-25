package com.project.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Card;
import com.project.repository.CardRepository;
import com.project.repository.ListRepository;

@RestController
@RequestMapping("/")
public class CardController {

	@Autowired
	public CardRepository cardRepository;
	@Autowired
	public ListRepository listRepository;

	@RequestMapping(path = "list/{id}/card", method = RequestMethod.POST)
	public Card addCard(@PathVariable("id") Integer id, @RequestBody Card card) {
		card.setList(listRepository.findOne(id));
		cardRepository.save(card);
		return card;
	}

	@RequestMapping(path = "getallcard/{listId}", method = RequestMethod.GET)
	public Set<Card> getAllCard(@PathVariable("listId") Integer listId) {
		return listRepository.findOne(listId).getCards();
	}

	@RequestMapping(path = "card/{id}", method = RequestMethod.DELETE)
	public void removeCard(@PathVariable("id") Integer id) {
		cardRepository.delete(id);
	}

	@RequestMapping(path = "card/{listId}", method = RequestMethod.POST)
	public Card InsertCard(@PathVariable("listId") Integer listId, @RequestBody Card card) {
		
		card.setList(listRepository.findOne(listId));
		cardRepository.save(card);
		return card;
	}
//	@RequestMapping(path = "cardsperproject/{projectId}",method = RequestMethod.GET)
//	public List<Card> getAllCardPerProject(@PathVariable("projectId") Integer projectId){
//		Card card = new Card();
//		card.setProjectId(projectId);
//		Example<Card> ex = Example.of(card);
//		return cardRepository.findAll(ex);
//	}
}
