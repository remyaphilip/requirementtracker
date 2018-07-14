package com.proman.api.project.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proman.api.project.model.Card;
import com.proman.api.project.repository.CardRepository;
import com.proman.api.project.repository.ListRepository;

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
	public boolean insertCard(@PathVariable("listId") Integer listId, @RequestBody Card card) {

		card.setList(listRepository.findOne(listId));
		cardRepository.save(card);
		return true;
	}

	@RequestMapping(path = "editcard/{listId}/{cardId}", method = RequestMethod.POST)
	public boolean editCard(@PathVariable("listId") Integer listId, @PathVariable("cardId") Integer cardId,
			@RequestBody Card card) {
		card.setList(listRepository.findOne(listId));
		card.setCardId(cardId);
		cardRepository.save(card);
		return true;
	}

	
	@RequestMapping(path = "removecard/{cardId}", method = RequestMethod.DELETE)
	public boolean editCard(@PathVariable("cardId") Integer cardId) {
		Card card = new Card();
		card.setCardId(cardId);
		cardRepository.delete(card);
		return true;
	}
	
}
