package com.jiuhao.jhjk.bean;

import java.util.List;

public class SystemBean {

    /**
     * status : 0
     * msg :
     * data : [{"id":6,"title":"处方订单已发货","symptom":"夏季感冒","orderId":"JH10000114920190820221405","createTime":"7月30 17:50","customerName":"昵称/名字","customerId":0,"existsExpress":1},{"id":3,"title":"处方订单已发货","symptom":"夏季感冒","orderId":"JH10000114920190820221405","createTime":"7月30 17:50","customerName":"昵称/名字","customerId":0,"existsExpress":1}]
     * hasNextPage : false
     * totalCount : 0
     */

    private int status;
    private String msg;
    private boolean hasNextPage;
    private int totalCount;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "SystemBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", hasNextPage=" + hasNextPage +
                ", totalCount=" + totalCount +
                ", data=" + data +
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

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 6
         * title : 处方订单已发货
         * symptom : 夏季感冒
         * orderId : JH10000114920190820221405
         * createTime : 7月30 17:50
         * customerName : 昵称/名字
         * customerId : 0
         * existsExpress : 1
         */

        private int id;
        private String title;
        private String symptom;
        private String orderId;
        private String createTime;
        private String customerName;
        private int customerId;
        private int existsExpress;

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", symptom='" + symptom + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", customerName='" + customerName + '\'' +
                    ", customerId=" + customerId +
                    ", existsExpress=" + existsExpress +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSymptom() {
            return symptom;
        }

        public void setSymptom(String symptom) {
            this.symptom = symptom;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getExistsExpress() {
            return existsExpress;
        }

        public void setExistsExpress(int existsExpress) {
            this.existsExpress = existsExpress;
        }
    }
}
