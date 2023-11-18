# assginment10
7月度、旧第10回の課題です。  
前回のプロジェクトを一部改変したものになります。
  
# 動作確認
[READ処理](https://github.com/mkdk72ki/assignment10/tree/feature/post#read%E5%87%A6%E7%90%86)  
[CREATE処理](https://github.com/mkdk72ki/assignment10/tree/feature/post#create%E5%87%A6%E7%90%86)  
[UPDATE処理]  
[DELETE処理]  
  
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

※`id:5, 6`は動作確認の際に登録、削除したため今回は`id:7`になっている  

    
メールアドレスが重複する場合は409エラーを返す。  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/2b8f565b-7c7c-4d40-99f9-8a9937c96860)


### バリデーション

- nameが空文字の場合  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/1b27e372-2fe4-472d-ab5a-0d3cbd2fe0ad)

- nameが20字以上の場合
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/76931ebc-1ea8-4ce6-8351-c0f0ab67ab3c)
  
  
- rubyが空文字の場合    
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/b79202c4-3945-4cf5-a2a7-ed63059a8858)
  
- rubyが50字以上の場合
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/00a67f34-e3de-4647-a10f-45869ca4c6cc)  

  
- birthdayが空文字の場合  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/64868698-85aa-4a61-b4c4-3515bf85a3af)
  
  
- emailが空文字の場合    
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/562e817b-35e3-4b0a-adc4-41f1608a9e46)

- emailが100字以上の場合
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/a83c6052-d81d-425d-83ba-9182ad88a096)  

- emailの形式が不適切な場合  
![emailValidate](https://github.com/mkdk72ki/assignment10/assets/143886913/6d36eccc-4831-4e48-89a3-78bb36e82e20)  
　　

## UPDATE処理
PATCHリクエストを実行すると、リクエストボディに含まれた情報でDBを更新し、メッセージをJSON形式で返す。

``` cURLコマンド
curl --location --request PATCH 'http://localhost:8080/user/7' \
--header 'Content-Type: application/json' \
--data-raw ' {
    "name": "谷口敦也",
    "ruby": "taniguchi atsuya",
    "birthday": "1998-06-26",
    "email": "taniguchi@mkdk.com"
}'
```

![delete前](https://github.com/mkdk72ki/assignment10/assets/143886913/1275a9d2-1f40-49d3-9c11-c03d36bacd44)  
![patchOk](https://github.com/mkdk72ki/assignment10/assets/143886913/94023e17-0e6f-4335-ab1c-af4e90b45070)  
![patchOkDb](https://github.com/mkdk72ki/assignment10/assets/143886913/71775836-84fc-4e51-9962-20c1144a1b2b)  
(画像3枚目ははdelete後のものを使用、差し替え予定)
  
## DELETE処理
DELETEリクエスト`http://localhost:8080/user/{id}`を実行すると、指定したIDに紐づいたレコードを削除し、メッセージをJSON形式で返す。
  
![delete前](https://github.com/mkdk72ki/assignment10/assets/143886913/8e407e5b-5560-4b40-9de0-0d3f5093394d)  
![deleteOk](https://github.com/mkdk72ki/assignment10/assets/143886913/79efd993-4aa6-4796-b783-79a63a1b08b6)  
![deleteOkDb](https://github.com/mkdk72ki/assignment10/assets/143886913/fa102c02-71df-460a-b4ae-7ad782d411a1)  

