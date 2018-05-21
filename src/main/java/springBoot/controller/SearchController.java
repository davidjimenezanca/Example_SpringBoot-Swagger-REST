/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package springBoot.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springBoot.model.News;
import springBoot.repo.CNNNewsRepository;

import java.util.Set;

@RestController
@Api(value="rest-cnn-news", description="RESTful web services for searching in CNN channels news.")
@RequestMapping("/rest-cnn-news/search")
public class SearchController {

    @Autowired
    private CNNNewsRepository repository;

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "getAll",
            notes = "Search news in Redis database - Using Method GET; format result response for CHANNEL parameter",
            nickname = "getAll")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of CNN news names found in all channel", response = Set.class )
    })
    public Set<News> getAll() {

        return repository.findAll();
    }

    @RequestMapping(value="/searchByChannel", method = RequestMethod.GET)
    @ApiOperation(value = "searchByChannel",
                  notes = "Search news in Redis database - Using Method GET; format result response for CHANNEL parameter",
                  nickname = "searchByChannel")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "List of CNN news names found in this channel", response = Set.class )
    })
    public Set<News> searchByChannel(@ApiParam(value = "Channel key for News DB", name="channel", required = true)
                                        @RequestParam(name="channel", required = true)  String channel) {

        return repository.findByChannel(channel);
    }

    @RequestMapping(value="/titleContains", method = RequestMethod.GET)
    @ApiOperation(value = "titleContains",
            notes = "Search news in Redis database - Using Method GET; format result response for TITLE parameter",
            nickname = "titleContains")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of CNN news names found that contain this title token", response = Set.class )
    })
    public Set<News> titleContains(@ApiParam(value = "Title key for News DB", name="title", required = true)
                                     @RequestParam(name="title", required = true)  String title) {

        return repository.findByTitle(title);
    }

    @RequestMapping(value="/linkContains", method = RequestMethod.GET)
    @ApiOperation(value = "linkContains",
            notes = "Search news in Redis database - Using Method GET; format result response for LINK parameter",
            nickname = "linkContains")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of CNN news names found that contain this link token", response = Set.class )
    })
    public Set<News> linkContains(@ApiParam(value = "Link key for News DB", name="link", required = true)
                                     @RequestParam(name="link", required = true)  String link) {

        return repository.findByLink(link);
    }

}