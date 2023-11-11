# assginment10
7月度、旧第10回の課題です。  
前回のプロジェクトを一部改変したものになります。
  
# 動作確認
[READ処理](https://github.com/mkdk72ki/assignment10/feature/post/README.md#read%E5%87%A6%E7%90%86)  
[CREATE処理](https://github.com/mkdk72ki/assignment10/feature/post/README.md#create%E5%87%A6%E7%90%86)  
  
## データベース
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/18177996-5230-4057-89d7-07c74f364ad0)  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/b218e984-2a4c-4164-a4dd-002548a88018)

  
## READ処理

### 全件取得

GETリクエスト `http://localhost:8080/users` を実行すると、格納された全てのレコードを取得し、JSON形式で返す。
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/7fe42ddf-959f-4aa3-8e4a-819923a886d2)  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/bdbc981a-6415-444c-aebe-93fc87fa5848)


### ルビ検索
  
GETリクエスト `http://localhost:8080/users?ruby={ruby}` を実行すると、取得したクエリパラメータとルビが前方一致したレコードを取得し、JSON形式で返す。  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/40fa355b-bad8-4925-96f1-5b68a26ec60d)  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/d57d6ac1-d491-4d12-97cd-d375a8e366d7)  

存在しないルビを指定した場合は404エラーを返す。  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/4673b643-53fe-4311-af56-eb346741cb79)


### IDによる取得
  
GETリクエスト `http://localhost:8080/user/{id}` を実行すると、IDに紐づいたレコードを取得し、JSON形式で返す。  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/9f80baf4-7809-4e7f-9e5f-3f375279e2ff)

存在しないIDを指定した場合は404エラーを返す。  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/3a9d4ff4-e1fd-4152-bdb7-90b131377771)  

  
## CREATE処理

POSTリクエストを実行すると、リクエストボディに含まれた情報をDBに登録し、メッセージをJSON形式で返す。

- cURLコマンド

```
curl --location 'http://localhost:8080/user' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "田村敦",
    "ruby": "tamura atsushi",
    "birthday": "1995-09-18",
    "email": "tamura@mkdk.com"
}'
```

![image](https://github.com/mkdk72ki/assignment10/assets/143886913/a17a9b99-d942-4d8b-a6cf-42c57f321652)  

![image](https://github.com/mkdk72ki/assignment10/assets/143886913/9ce5cf8a-27d7-4385-8db3-08f400b490ff)　　

※`id:5, 6`は動作確認の際に登録し削除したため今回は`id:7`になっている  

    
メールアドレスが重複する場合は409エラーを返す。  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/2b8f565b-7c7c-4d40-99f9-8a9937c96860)


### バリデーション

- name  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/1b27e372-2fe4-472d-ab5a-0d3cbd2fe0ad)  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/76931ebc-1ea8-4ce6-8351-c0f0ab67ab3c)  

- ruby  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/b79202c4-3945-4cf5-a2a7-ed63059a8858)  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/00a67f34-e3de-4647-a10f-45869ca4c6cc)  

- birthday  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/64868698-85aa-4a61-b4c4-3515bf85a3af)


- email  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/562e817b-35e3-4b0a-adc4-41f1608a9e46)  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/a83c6052-d81d-425d-83ba-9182ad88a096)


