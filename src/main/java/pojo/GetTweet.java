package pojo;

public class GetTweet {
	private DataGetTweet data;
	private IncludesGetTweet includes;
	
	public DataGetTweet getData() {
		return data;
	}
	public void setData(DataGetTweet data) {
		this.data = data;
	}
	public IncludesGetTweet getIncludes() {
		return includes;
	}
	public void setIncludes(IncludesGetTweet includes) {
		this.includes = includes;
	}

}

//examenple:
/*{
    "data": {
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
        "id": "1345889499625222145"
    },
    "includes": {
        "users": [
            {
                "id": "18927441",
                "name": "IGN",
                "username": "IGN"
            }
        ]
    }
}*/