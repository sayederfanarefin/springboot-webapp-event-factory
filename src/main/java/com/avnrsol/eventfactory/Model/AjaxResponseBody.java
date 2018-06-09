package com.avnrsol.eventfactory.Model;

import java.util.List;


    public class AjaxResponseBody {

        String msg;
        List<Serviceo> result;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<Serviceo> getResult() {
            return result;
        }

        public void setResult(List<Serviceo> result) {
            this.result = result;
        }
    }

