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
package springBoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;
import springBoot.model.News;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {

        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, News> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, News> template = new RedisTemplate<String, News>();
        template.setConnectionFactory(lettuceConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new XMlRedisSerializer());
        return template;
    }

    static class XMlRedisSerializer implements RedisSerializer<Object> {

        private Marshaller marshaller;
        private Unmarshaller unmarshaller;

        public XMlRedisSerializer() {
        }

        public XMlRedisSerializer(Marshaller marshaller, Unmarshaller unmarshaller) {
            this.marshaller = marshaller;
            this.unmarshaller = unmarshaller;
            this.afterPropertiesSet();
        }

        public void afterPropertiesSet() {
            Assert.notNull(this.marshaller, "non-null marshaller required");
            Assert.notNull(this.unmarshaller, "non-null unmarshaller required");
        }

        public void setMarshaller(Marshaller marshaller) {

            this.marshaller = marshaller;
        }

        public void setUnmarshaller(Unmarshaller unmarshaller) {

            this.unmarshaller = unmarshaller;
        }

        public Object deserialize(byte[] bytes) throws SerializationException {

            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(News.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                return (News) jaxbUnmarshaller.unmarshal(new StreamSource(new ByteArrayInputStream(bytes)));
            } catch (Exception var3) {
                throw new SerializationException("Cannot deserialize bytes", var3);
            }
        }

        public byte[] serialize(Object t) throws SerializationException {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(stream);
            try {
                this.marshaller.marshal(t, result);
            } catch (Exception var5) {
                throw new SerializationException("Cannot serialize object", var5);
            }

            return stream.toByteArray();
        }
    }

}