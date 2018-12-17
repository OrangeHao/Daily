package com.orange.module_news.model;

import java.util.List;

/**
 * created by czh on 2018/5/9
 */
public class NewsListItemBean {

    /**
     * status : ok
     * count : 24
     * count_total : 65627
     * pages : 2735
     * posts : [{"id":96450,"url":"http://i.jandan.net/2018/05/07/dating-life.html","title":"「行为免疫系统」会破坏约会","excerpt":"\u201c行为免疫系统\u201d的激活似乎会抑制人们对同龄人的社交欲望。","date":"2018-05-07 14:00:31","tags":[{"id":687,"slug":"%e6%97%a0%e5%8e%98%e5%a4%b4%e7%a0%94%e7%a9%b6","title":"无厘头研究","description":"","post_count":2846}],"author":{"id":563,"slug":"siyi","name":"蛋花","first_name":"","last_name":"","nickname":"蛋花","url":"","description":""},"comment_count":7,"comment_status":"open","custom_fields":{"thumb_c":["http://img.jandan.net/news/2018/05/4b1a5af6e86195ef283dbe9fbc65c8b1.jpg"]}},{"id":96463,"url":"http://i.jandan.net/2018/05/07/stem-cells.html","title":"MIT制造出模拟禁食效果的分子，使肠道干细胞再生","excerpt":"禁食24小时能够提高肠道干细胞再生能力，科学家制造出了模拟该过程的分子。","date":"2018-05-07 13:00:54","tags":[{"id":831,"slug":"%e5%81%a5%e5%ba%b7","title":"健康","description":"","post_count":1316}],"author":{"id":614,"slug":"rare","name":"Rare","first_name":"","last_name":"","nickname":"Rare","url":"","description":""},"comment_count":7,"comment_status":"open","custom_fields":{"thumb_c":["http://img.jandan.net/news/2018/05/c139019254b21614e5047d3fe245b1fc.jpg"]}}}]
     */

    private String status;
    private int count;
    private int count_total;
    private int pages;
    private List<PostsBean> posts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount_total() {
        return count_total;
    }

    public void setCount_total(int count_total) {
        this.count_total = count_total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<PostsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsBean> posts) {
        this.posts = posts;
    }

    public static class PostsBean {
        /**
         * id : 96450
         * url : http://i.jandan.net/2018/05/07/dating-life.html
         * title : 「行为免疫系统」会破坏约会
         * excerpt : “行为免疫系统”的激活似乎会抑制人们对同龄人的社交欲望。
         * date : 2018-05-07 14:00:31
         * tags : [{"id":687,"slug":"%e6%97%a0%e5%8e%98%e5%a4%b4%e7%a0%94%e7%a9%b6","title":"无厘头研究","description":"","post_count":2846}]
         * author : {"id":563,"slug":"siyi","name":"蛋花","first_name":"","last_name":"","nickname":"蛋花","url":"","description":""}
         * comment_count : 7
         * comment_status : open
         * custom_fields : {"thumb_c":["http://img.jandan.net/news/2018/05/4b1a5af6e86195ef283dbe9fbc65c8b1.jpg"]}
         */

        private int id;
        private String url;
        private String title;
        private String excerpt;
        private String date;
        private AuthorBean author;
        private int comment_count;
        private String comment_status;
        private CustomFieldsBean custom_fields;
        private List<TagsBean> tags;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(String excerpt) {
            this.excerpt = excerpt;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public String getComment_status() {
            return comment_status;
        }

        public void setComment_status(String comment_status) {
            this.comment_status = comment_status;
        }

        public CustomFieldsBean getCustom_fields() {
            return custom_fields;
        }

        public void setCustom_fields(CustomFieldsBean custom_fields) {
            this.custom_fields = custom_fields;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class AuthorBean {
            /**
             * id : 563
             * slug : siyi
             * name : 蛋花
             * first_name :
             * last_name :
             * nickname : 蛋花
             * url :
             * description :
             */

            private int id;
            private String slug;
            private String name;
            private String first_name;
            private String last_name;
            private String nickname;
            private String url;
            private String description;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class CustomFieldsBean {
            private List<String> thumb_c;

            public List<String> getThumb_c() {
                return thumb_c;
            }

            public void setThumb_c(List<String> thumb_c) {
                this.thumb_c = thumb_c;
            }
        }

        public static class TagsBean {
            /**
             * id : 687
             * slug : %e6%97%a0%e5%8e%98%e5%a4%b4%e7%a0%94%e7%a9%b6
             * title : 无厘头研究
             * description :
             * post_count : 2846
             */

            private int id;
            private String slug;
            private String title;
            private String description;
            private int post_count;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getPost_count() {
                return post_count;
            }

            public void setPost_count(int post_count) {
                this.post_count = post_count;
            }
        }
    }
}
