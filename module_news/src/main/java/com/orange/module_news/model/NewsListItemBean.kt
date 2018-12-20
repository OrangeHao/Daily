package com.orange.module_news.model

/**
 * created by czh on 2018/5/9
 */
data class NewsListItemBean(
    val count: Int,
    val count_total: Int,
    val pages: Int,
    val posts: List<Post>,
    val status: String
)

data class Post(
    val author: Author,
    val comment_count: Int,
    val comment_status: String,
    val custom_fields: CustomFields,
    val date: String,
    val excerpt: String,
    val id: Int,
    val tags: List<Tag>,
    val title: String,
    val url: String
)

data class Tag(
    val description: String,
    val id: Int,
    val post_count: Int,
    val slug: String,
    val title: String
)

data class CustomFields(
    val thumb_c: List<String>
)

data class Author(
    val description: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val name: String,
    val nickname: String,
    val slug: String,
    val url: String
)