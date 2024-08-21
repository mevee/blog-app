package com.example.blog_webapp.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class GenericResponse<T> extends CommonApi{
     public T data;
     public Meta meta;

     public GenericResponse() {
     }

     public GenericResponse(T data, Meta meta) {
          this.data = data;
          this.meta = meta;
     }

     public GenericResponse(Meta meta) {
          this.meta = meta;
     }
}
