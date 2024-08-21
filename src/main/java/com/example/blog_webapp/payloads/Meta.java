package com.example.blog_webapp.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class Meta {
     public int status;
     public String statusMessage;
     public String requestId;

     public Meta(int status, String statusMessage, String requestId) {
          this.status = status;
          this.statusMessage = statusMessage;
          this.requestId = requestId;
     }

     public Meta() {
     }
}
