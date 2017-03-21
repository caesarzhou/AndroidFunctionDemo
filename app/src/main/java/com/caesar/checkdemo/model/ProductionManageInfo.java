package com.caesar.checkdemo.model;

import java.util.List;

/**
 * 作品管家首页信息
 * Created by caesar on 2016/11/29.
 * 修订历史:
 */
public class ProductionManageInfo {

    /**
     * code : 1
     * status : 1
     * msg : 所有图片
     * data : [{"album_title":"2016-11-29","photo_num":"2","image_list":[{"img":"http://7xjro5.com1.z0.glb.clouddn.com/upload/2016/11/29/20161129095510258.jpg","photo_id":"925","date":"2016-11-29 09:55:10"},{"img":"http://7xjro5.com1.z0.glb.clouddn.com/upload/2016/11/29/20161129095510767.jpg","photo_id":"926","date":"2016-11-29 09:55:10"}]},{"album_title":"2016-11-28","photo_num":"2","image_list":[{"img":"http://7xjro5.com1.z0.glb.clouddn.com/upload/2016/11/28/20161128095942774.jpg","photo_id":"917","date":"2016-11-28 21:59:42"},{"img":"http://7xjro5.com1.z0.glb.clouddn.com/upload/2016/11/28/20161128095942401.jpg","photo_id":"918","date":"2016-11-28 21:59:42"}]}]
     * pages : 1
     */

    private String code;
    private String status;
    private String msg;
    private String pages;
    private List<DataEntity> data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public String getPages() {
        return pages;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * album_title : 2016-11-29
         * photo_num : 2
         * image_list : [{"img":"http://7xjro5.com1.z0.glb.clouddn.com/upload/2016/11/29/20161129095510258.jpg","photo_id":"925","date":"2016-11-29 09:55:10"},{"img":"http://7xjro5.com1.z0.glb.clouddn.com/upload/2016/11/29/20161129095510767.jpg","photo_id":"926","date":"2016-11-29 09:55:10"}]
         */

        private String album_title;
        private String photo_num;
        private List<ImageListEntity> image_list;

        public void setAlbum_title(String album_title) {
            this.album_title = album_title;
        }

        public void setPhoto_num(String photo_num) {
            this.photo_num = photo_num;
        }

        public void setImage_list(List<ImageListEntity> image_list) {
            this.image_list = image_list;
        }

        public String getAlbum_title() {
            return album_title;
        }

        public String getPhoto_num() {
            return photo_num;
        }

        public List<ImageListEntity> getImage_list() {
            return image_list;
        }

        public static class ImageListEntity {
            /**
             * img : http://7xjro5.com1.z0.glb.clouddn.com/upload/2016/11/29/20161129095510258.jpg
             * photo_id : 925
             * date : 2016-11-29 09:55:10
             */

            private String img;
            private String photo_id;
            private String date;

            public void setImg(String img) {
                this.img = img;
            }

            public void setPhoto_id(String photo_id) {
                this.photo_id = photo_id;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getImg() {
                return img;
            }

            public String getPhoto_id() {
                return photo_id;
            }

            public String getDate() {
                return date;
            }
        }
    }
}
