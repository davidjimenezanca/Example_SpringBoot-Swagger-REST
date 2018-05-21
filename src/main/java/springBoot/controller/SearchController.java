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

import java.util.LinkedHashSet;
import java.util.Set;

@RestController
@Api(value="rest-cnn-news", description="RESTful web services for searching in CNN channels news.")
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private CNNNewsRepository repository;

    @RequestMapping(value="/searchByChannel", method = RequestMethod.GET)
    @ApiOperation(value = "searchByChannel",
                  notes = "Search news in Redis database - Using Method GET; format result response for CHANNEL parameter",
                  nickname = "searchByChannel")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "List of CNN news names found in this channel", response = Set.class )
    })
    public Set<News> searchByChannel(@ApiParam(value = "Channel key for News DB", name="channel", required = true)
                                        @RequestParam(name="channel", required = true)  String channel) {

        Set<News> response = new LinkedHashSet<>();
        response.addAll(repository.findByChannel(channel));
        return response;
    }

}