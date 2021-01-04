package pojo;

public class DataGetTweet {
	private String author_id;
	private String created_at;
	private String text;
	private AttachmentsGetTweet attachments;
	private String id;
	
	public String getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public AttachmentsGetTweet getAttachments() {
		return attachments;
	}
	public void setAttachments(AttachmentsGetTweet attachments) {
		this.attachments = attachments;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
/*
"author_id": "18927441",
"created_at": "2021-01-04T00:27:16.000Z",
"text": "The PS5 already has a great lineup, from exclusives like Demon's Souls to PS4 games like Ghost of Tsushima rounding out our list that got some notable next-gen improvements. \n\nhttps://t.co/zaJas85ym7 https://t.co/6dXwR8OjQE",
"attachments": {
    "media_keys": [
        "3_1345889343861370883",
        "3_1345889377935888384",
        "3_1345889391848390661",
        "3_1345889409552556033"
    ]
},
"id": "1345889499625222145"*/