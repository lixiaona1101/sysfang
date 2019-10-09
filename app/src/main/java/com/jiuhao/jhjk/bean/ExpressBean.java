package com.jiuhao.jhjk.bean;

import java.util.List;

public class ExpressBean {

    /**
     * status : 0
     * msg : ok
     * result : {"number":"SF1011431250554","type":"sfexpress","typename":"顺丰速运","logo":"","endCity":"开封市","startCity":"郑州市","deliverystatus":3,"issign":1,"list":[{"time":"2019-09-07 18:14:01","status":"已签收,感谢使用顺丰,期待再次为您服务"},{"time":"2019-09-07 09:25:27","status":"快件到达顺丰店/站 【KHJCD豫康新城30栋】"},{"time":"2019-09-07 08:00:19","status":"快件交给文辉，正在派送途中（联系电话：18703633048）"},{"time":"2019-09-07 07:31:30","status":"快件到达 【郑州市航空港区锦绣荷园营业点】"},{"time":"2019-09-07 05:52:57","status":"快件已发车"},{"time":"2019-09-07 02:57:20","status":"快件在【郑州港区中转场】已装车,准备发往 【郑州市航空港区锦绣荷园营业点】"},{"time":"2019-09-07 01:22:37","status":"快件到达 【郑州港区中转场】"},{"time":"2019-09-07 00:31:02","status":"快件已发车"},{"time":"2019-09-07 00:19:46","status":"快件在【郑州经开集散中心】已装车,准备发往 【郑州港区中转场】"},{"time":"2019-09-06 23:51:26","status":"快件到达 【郑州经开集散中心】"},{"time":"2019-09-06 21:43:24","status":"快件已发车"},{"time":"2019-09-06 21:39:40","status":"快件在【郑州市中原区丁香里路营业点】已装车,准备发往 【郑州经开集散中心】"},{"time":"2019-09-06 20:34:42","status":"顺丰速运 已收取快件"}]}
     */

    private int status;
    private String msg;
    private ResultBean result;

    @Override
    public String toString() {
        return "ExpressBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * number : SF1011431250554
         * type : sfexpress
         * typename : 顺丰速运
         * logo :
         * endCity : 开封市
         * startCity : 郑州市
         * deliverystatus : 3
         * issign : 1
         * list : [{"time":"2019-09-07 18:14:01","status":"已签收,感谢使用顺丰,期待再次为您服务"},{"time":"2019-09-07 09:25:27","status":"快件到达顺丰店/站 【KHJCD豫康新城30栋】"},{"time":"2019-09-07 08:00:19","status":"快件交给文辉，正在派送途中（联系电话：18703633048）"},{"time":"2019-09-07 07:31:30","status":"快件到达 【郑州市航空港区锦绣荷园营业点】"},{"time":"2019-09-07 05:52:57","status":"快件已发车"},{"time":"2019-09-07 02:57:20","status":"快件在【郑州港区中转场】已装车,准备发往 【郑州市航空港区锦绣荷园营业点】"},{"time":"2019-09-07 01:22:37","status":"快件到达 【郑州港区中转场】"},{"time":"2019-09-07 00:31:02","status":"快件已发车"},{"time":"2019-09-07 00:19:46","status":"快件在【郑州经开集散中心】已装车,准备发往 【郑州港区中转场】"},{"time":"2019-09-06 23:51:26","status":"快件到达 【郑州经开集散中心】"},{"time":"2019-09-06 21:43:24","status":"快件已发车"},{"time":"2019-09-06 21:39:40","status":"快件在【郑州市中原区丁香里路营业点】已装车,准备发往 【郑州经开集散中心】"},{"time":"2019-09-06 20:34:42","status":"顺丰速运 已收取快件"}]
         */

        private String number;
        private String type;
        private String typename;
        private String logo;
        private String endCity;
        private String startCity;
        private int deliverystatus;
        private int issign;
        private List<ListBean> list;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "number='" + number + '\'' +
                    ", type='" + type + '\'' +
                    ", typename='" + typename + '\'' +
                    ", logo='" + logo + '\'' +
                    ", endCity='" + endCity + '\'' +
                    ", startCity='" + startCity + '\'' +
                    ", deliverystatus=" + deliverystatus +
                    ", issign=" + issign +
                    ", list=" + list +
                    '}';
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getEndCity() {
            return endCity;
        }

        public void setEndCity(String endCity) {
            this.endCity = endCity;
        }

        public String getStartCity() {
            return startCity;
        }

        public void setStartCity(String startCity) {
            this.startCity = startCity;
        }

        public int getDeliverystatus() {
            return deliverystatus;
        }

        public void setDeliverystatus(int deliverystatus) {
            this.deliverystatus = deliverystatus;
        }

        public int getIssign() {
            return issign;
        }

        public void setIssign(int issign) {
            this.issign = issign;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * time : 2019-09-07 18:14:01
             * status : 已签收,感谢使用顺丰,期待再次为您服务
             */

            private String time;
            private String status;

            @Override
            public String toString() {
                return "ListBean{" +
                        "time='" + time + '\'' +
                        ", status='" + status + '\'' +
                        '}';
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }

}
