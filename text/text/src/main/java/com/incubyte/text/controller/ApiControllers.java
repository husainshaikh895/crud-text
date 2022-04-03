package com.incubyte.text.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import models.Word;
import repo.WordRepo;

@RestController
public class ApiControllers {
	@Autowired
	private WordRepo wordRepo;
	
	
	@GetMapping(value = "/")
	public String getPage()
	{
		return "Hello Spring";
	}
	
	@GetMapping(value = "/words")
	public List<Word> getWords()
	{
		return wordRepo.findAll();
	}
	
	@PostMapping(value = "/save")
	public String saveWord(@RequestBody Word word)
	{
		wordRepo.save(word);
		return "Saved...";
	}
	
	@PutMapping(value = "/update/{id}")
	public String updateWord(@PathVariable Long id, @RequestBody Word word)
	{
		Optional<Word> wordToBeUpdated = wordRepo.findById(id);
		if(wordToBeUpdated.isPresent())
		{
			wordToBeUpdated.get().setWord(word.getWord());
			wordRepo.save(wordToBeUpdated.get());
			return "Updated...";
		}
		return "id doesn't exists...";
	}
	
	
	@DeleteMapping(value = "/delete/{id}")
	public String deleteWord(@PathVariable Long id)
	{
		Optional<Word> word = wordRepo.findById(id);
		if(word.isPresent())
		{			
			wordRepo.delete(word.get());
			return "Deleted...";
		}
		return "id doesn't exists...";
	}
}
