/*
 * Copyright 2016-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package springBoot.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

import javax.xml.transform.Result;

@XmlRootElement(name = "news")
@XmlAccessorType(XmlAccessType.FIELD)
public class News implements Serializable {

    private static final long serialVersionUID = 3775592430977571669L;

    @XmlElement(required = true)
    private String title;

    @XmlElement(required = true)
    private String link;

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getLink() {

        return link;
    }

    public void setLink(String link) {

        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return Objects.equals(getTitle(), news.getTitle()) &&
                Objects.equals(getLink(), news.getLink());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getTitle(), getLink());
    }

}