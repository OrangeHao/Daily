package com.orange.module_news.model;

/**
 * created by czh on 2018/7/28
 */
public class NewsDetail {


    /**
     * status : ok
     * post : {"id":97943,"content":"<p>虽然身为一名员工，在公司里不认真工作可不行。但大家都是凡人，有时候也需要理解。在工作时，无意中也会昏昏欲睡。在温暖的办公室里待久了，偶尔也会打个小盹。<\/p>\n<p><img src=\"http://img.jandan.net/news/2018/07/1223df1bd19135ca65307bb8889a6ccf.jpg\" alt=\"日本欲开发「防睡觉监视系统」，引发社畜强烈抗议\" /><\/p>\n<p>然而，就在近日，日本出现了一个连打个小盹都无法容忍的人工智能系统。据朝日新闻报道，这个人工智能系统会一直监视员工的眼皮，当检测到有人昏昏欲睡时，就会降低室内空调的温度，用冷风吹走员工的睡意。<\/p>\n<h4>绝对唤醒员工<\/h4>\n<p>2018年7月25日，ダイキン工业公司与日本电气公司宣称将共同开发在办公室内防止员工睡觉的空调系统。该系统的工作原理如下：<\/p>\n<h4 class=\"pullquote\">\n<ul>1. 通过与电脑连接的摄像头，追踪员工眼皮的运动。<\/ul>\n<ul>2. 检测到眼皮运动方式发生变化。<\/ul>\n<ul>3. AI「啊，这家伙要开始打盹了」<\/ul>\n<ul>4. 那好吧，我就把空调的设定温度降低几度。<\/ul>\n<ul>5. 社畜给我醒过来啊啊啊啊啊！<\/ul>\n<\/h4>\n<p>大概就这么个意思。<\/p>\n<p>可是，如果这么做的话，要是一个人昏昏欲睡，所有人都要一起挨冻吗？这一点你大可放心。据ダイキン公司表示，正考虑将冷风设计为只对着昏昏欲睡的员工吹。<\/p>\n<h4>网上的声音<\/h4>\n<p>对于这个新系统，网友们感叹连连。<\/p>\n<h4 class=\"pullquote\">「超棒的奴隶制度呢。顺便在脚上锁个铁球，再让科长拿着鞭子抽打我们如何？」<\/p>\n<p>「真是厉害呢。与『让人工智能代替人类工作』背道而驰的『让人工智能监视人类工作』就是我们所追求的未来？」<\/p>\n<p>「在促进人类创造力的方面点错科技树了吧。」<\/p>\n<p>「打个盹的话，即使只有15分钟，大脑也会神清气爽，能够有效提高生产力吧？」<\/p>\n<p>「用人工智能监视人类工作之前，先用人工智能监视黑心企业吧！」<\/p>\n<p>「办公室就算了，国会里才是必备吧？」<\/h4>\n<h4>2年后开始实用化？<\/h4>\n<p>在不久的未来，也就是明年3月之前，经过实用性实验与课题调查之后，就会推出正式的开发计划。2020年实用化之后，对于企图提高生产力的企业来说可谓喜大普奔，而日本全国的社畜们可就要遭殃了！<\/p>\n<p><em>本文译自  <a target=_blank href=\"https://rocketnews24.com/2018/07/26/1095540/\">ロケットニュース24<\/a>，由译者 <a target=_blank href=\"http://i.jandan.net/2018/07/28/anti-sleep.html\">HTT110<\/a> 基于创作共用协议(BY-NC)发布。<\/em><\/p>\n","date":"2018-07-28 12:00:54","modified":"2018-07-27 22:16:46"}
     * previous_url : http://i.jandan.net/2018/07/28/japan-iphone.html
     * next_url : http://i.jandan.net/2018/07/28/chronic-wounds.html
     */

    private String status;
    private PostBean post;
    private String previous_url;
    private String next_url;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PostBean getPost() {
        return post;
    }

    public void setPost(PostBean post) {
        this.post = post;
    }

    public String getPrevious_url() {
        return previous_url;
    }

    public void setPrevious_url(String previous_url) {
        this.previous_url = previous_url;
    }

    public String getNext_url() {
        return next_url;
    }

    public void setNext_url(String next_url) {
        this.next_url = next_url;
    }

    public static class PostBean {
        /**
         * id : 97943
         * content : <p>虽然身为一名员工，在公司里不认真工作可不行。但大家都是凡人，有时候也需要理解。在工作时，无意中也会昏昏欲睡。在温暖的办公室里待久了，偶尔也会打个小盹。</p>
         <p><img src="http://img.jandan.net/news/2018/07/1223df1bd19135ca65307bb8889a6ccf.jpg" alt="日本欲开发「防睡觉监视系统」，引发社畜强烈抗议" /></p>
         <p>然而，就在近日，日本出现了一个连打个小盹都无法容忍的人工智能系统。据朝日新闻报道，这个人工智能系统会一直监视员工的眼皮，当检测到有人昏昏欲睡时，就会降低室内空调的温度，用冷风吹走员工的睡意。</p>
         <h4>绝对唤醒员工</h4>
         <p>2018年7月25日，ダイキン工业公司与日本电气公司宣称将共同开发在办公室内防止员工睡觉的空调系统。该系统的工作原理如下：</p>
         <h4 class="pullquote">
         <ul>1. 通过与电脑连接的摄像头，追踪员工眼皮的运动。</ul>
         <ul>2. 检测到眼皮运动方式发生变化。</ul>
         <ul>3. AI「啊，这家伙要开始打盹了」</ul>
         <ul>4. 那好吧，我就把空调的设定温度降低几度。</ul>
         <ul>5. 社畜给我醒过来啊啊啊啊啊！</ul>
         </h4>
         <p>大概就这么个意思。</p>
         <p>可是，如果这么做的话，要是一个人昏昏欲睡，所有人都要一起挨冻吗？这一点你大可放心。据ダイキン公司表示，正考虑将冷风设计为只对着昏昏欲睡的员工吹。</p>
         <h4>网上的声音</h4>
         <p>对于这个新系统，网友们感叹连连。</p>
         <h4 class="pullquote">「超棒的奴隶制度呢。顺便在脚上锁个铁球，再让科长拿着鞭子抽打我们如何？」</p>
         <p>「真是厉害呢。与『让人工智能代替人类工作』背道而驰的『让人工智能监视人类工作』就是我们所追求的未来？」</p>
         <p>「在促进人类创造力的方面点错科技树了吧。」</p>
         <p>「打个盹的话，即使只有15分钟，大脑也会神清气爽，能够有效提高生产力吧？」</p>
         <p>「用人工智能监视人类工作之前，先用人工智能监视黑心企业吧！」</p>
         <p>「办公室就算了，国会里才是必备吧？」</h4>
         <h4>2年后开始实用化？</h4>
         <p>在不久的未来，也就是明年3月之前，经过实用性实验与课题调查之后，就会推出正式的开发计划。2020年实用化之后，对于企图提高生产力的企业来说可谓喜大普奔，而日本全国的社畜们可就要遭殃了！</p>
         <p><em>本文译自  <a target=_blank href="https://rocketnews24.com/2018/07/26/1095540/">ロケットニュース24</a>，由译者 <a target=_blank href="http://i.jandan.net/2018/07/28/anti-sleep.html">HTT110</a> 基于创作共用协议(BY-NC)发布。</em></p>

         * date : 2018-07-28 12:00:54
         * modified : 2018-07-27 22:16:46
         */

        private int id;
        private String content;
        private String date;
        private String modified;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }
    }
}
