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

import org.junit.Test;
import springBoot.BaseTestCase;
import springBoot.model.News;

import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class SearchControllerTest extends BaseTestCase {

    @Test
    public void testSearchNoResult() {

        Set<News> noNews = repository.findByChannel("SILICEA");
        assertTrue(noNews.size() == 0 );
    }

    @Test
    public void testSearchListResultLatest() {

        Set<News> noNews = repository.findByChannel("CNN_latest");
        assertFalse(noNews.size() == 0 );
    }

    @Test
    public void testSearchListResultSports() {

        Set<News> noNews = repository.findByChannel("CNN_sports");
        assertFalse(noNews.size() == 0 );
    }

    @Test
    public void testSearchListResultEurope() {

        Set<News> noNews = repository.findByChannel("CNN_europe");
        assertFalse(noNews.size() == 0 );
    }

    @Test
    public void testSearchListResultMoney() {

        Set<News> noNews = repository.findByChannel("CNN_money");
        assertFalse(noNews.size() == 0 );
    }

}