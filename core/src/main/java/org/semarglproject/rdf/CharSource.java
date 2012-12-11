/*
 * Copyright 2012 Lev Khomich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.semarglproject.rdf;

import java.io.Reader;

public class CharSource implements MainSource<Reader, CharSink> {

    private CharSink sink;

    public CharSource() {
    }

    @Override
    public DataProcessor<Reader> build() {
        return new DataProcessor<Reader>() {
            @Override
            void process(Reader source) throws ParseException {
                sink.startStream();
                sink.read(source);
                sink.endStream();
            }

            @Override
            boolean isStreamFinished() {
                return true;
            }

            @Override
            void endStream() throws ParseException {
            }

            @Override
            void setBaseUri(String baseUri) {
                sink.setBaseUri(baseUri);
            }
        };
    }

    @Override
    public CharSource streamingTo(CharSink sink) {
        this.sink = sink;
        return this;
    }
}