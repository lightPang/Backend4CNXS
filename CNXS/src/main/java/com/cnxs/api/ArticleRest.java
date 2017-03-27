package com.cnxs.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cnxs.bo.Article;
import com.cnxs.service.ArticleService;

@RestController
@RequestMapping(value = { "Articles/v1" })
public class ArticleRest {
	
	@Autowired
	private ArticleService articleSrv;
	
	@RequestMapping(value = "/Type/{type}/id/{id}",method = RequestMethod.GET)
    public ResponseEntity<Article> getArticleByTypeAndId(@PathVariable String type,@PathVariable int id) {
		Article res = articleSrv.getArtiCleByIdAndType(type, id);
        return res == null ? new ResponseEntity<Article>(res, HttpStatus.NOT_FOUND)  : new ResponseEntity<Article>(res, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/Type/{type}", method = RequestMethod.POST) 
	public ResponseEntity<Boolean> createArticle(@PathVariable String type, @RequestBody Article article) {
		boolean success = articleSrv.create(article, type);
		return success ? new ResponseEntity<>(success, HttpStatus.CREATED) : new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/Type/{type}/list", method = RequestMethod.GET) 
    public @ResponseBody List<Article> listTypeArticle(@PathVariable String type, @RequestParam(required=false) Integer limit, @RequestParam(required=false) Integer offset) {
        return articleSrv.getArticleList(type, offset, limit);
    }
	
	@RequestMapping(value = "/News", method = RequestMethod.GET)
	public @ResponseBody List<Article> getNewsList() {
	    return articleSrv.getNewsList();
	}
	
}
