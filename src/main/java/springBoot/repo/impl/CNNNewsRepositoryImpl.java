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
package springBoot.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;
import springBoot.model.News;
import springBoot.repo.CNNNewsRepository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.Set;

@Repository
public class CNNNewsRepositoryImpl implements CNNNewsRepository {

    private enum CHANNELS {
        CNN_latest,
        CNN_sports,
        CNN_world,
        CNN_money
    }

    @Autowired
    private RedisTemplate<String, News> redisTemplate;

    @Resource(name="redisTemplate")
    private SetOperations<String, News> setOps;

    @PostConstruct
    private void init() {

    }

    public void deleteAll() {
        setOps.getOperations().delete(CHANNELS.CNN_latest.toString());
        setOps.getOperations().delete(CHANNELS.CNN_sports.toString());
        setOps.getOperations().delete(CHANNELS.CNN_world.toString());
        setOps.getOperations().delete(CHANNELS.CNN_money.toString());
    }

    public Set<News> findAll() {
        Set<News> all = new LinkedHashSet<News>();
        all.addAll(setOps.members(CHANNELS.CNN_latest.toString()));
        all.addAll(setOps.members(CHANNELS.CNN_sports.toString()));
        all.addAll(setOps.members(CHANNELS.CNN_world.toString()));
        all.addAll(setOps.members(CHANNELS.CNN_money.toString()));
        return all;
    }

    @Override
    public Set<News> findByChannel(String channel) {
        return setOps.members(channel);
    }

    @Override
    public Set<News> findByTitle(String title) {
        return null;
    }

    @Override
    public Set<News> findByLink(String link) {
        return null;
    }

}
