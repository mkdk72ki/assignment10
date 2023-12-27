[![codecov](https://codecov.io/gh/mkdk72ki/assignment10/graph/badge.svg?token=ESZ8G4XUWC)](https://codecov.io/gh/mkdk72ki/assignment10)

# assginment10
7月度、旧第10回の課題です。  
前回のプロジェクトを一部改変したものになります。
  
# 動作確認
[READ処理](https://github.com/mkdk72ki/assignment10/tree/feature/patch-delete#read%E5%87%A6%E7%90%86)  
[CREATE処理](https://github.com/mkdk72ki/assignment10/tree/feature/patch-delete#create%E5%87%A6%E7%90%86)  
[UPDATE処理](https://github.com/mkdk72ki/assignment10/tree/feature/patch-delete#update%E5%87%A6%E7%90%86)  
[DELETE処理](https://github.com/mkdk72ki/assignment10/tree/feature/patch-delete#delete%E5%87%A6%E7%90%86)  
  
## データベース
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/18177996-5230-4057-89d7-07c74f364ad0)  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/b218e984-2a4c-4164-a4dd-002548a88018)


## READ処理

### 全件取得

GETリクエスト `http://localhost:8080/users` を実行すると、格納された全てのレコードを取得し、JSON形式で返す。
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/7fe42ddf-959f-4aa3-8e4a-819923a886d2)  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/bdbc981a-6415-444c-aebe-93fc87fa5848)


### ルビ検索
  
GETリクエスト `http://localhost:8080/users?rubyStartingWith={ruby}` を実行すると、取得したクエリパラメータとルビが前方一致したレコードを取得し、JSON形式で返す。  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/515aa0a3-ed9d-4b3d-bd0d-53b6095451d2)  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/5d4289df-7532-4f6d-b132-c7a85a2c200a)
 

存在しないルビを指定した場合は空のリストを返す。  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/72926770-d8f9-470a-bccc-ef1cecb271db)



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
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/5b1d007a-c087-430a-9182-ab66fb03fa69)  

- nameが20字以上の場合
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/f5d21931-1778-4ca3-874e-02a556e269fd)

  
- rubyが空文字の場合    
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/b7e23163-5421-4282-adab-aa99c64e0a58)  
  
- rubyが50字以上の場合
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/36ed249c-1102-4ad9-abc1-554dae39556b)
  

- birthdayが空文字の場合  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/ff3a8e7a-8243-4d3a-831b-8452a7ebb207)
  

- emailが空文字の場合    
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/0a63572d-7a84-4079-851d-c141dde63212)  

- emailが100字以上の場合
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/58a8bec3-5daf-4ab5-96d9-c4d79d12d774)

- emailの形式が不適切な場合  
![image](https://github.com/mkdk72ki/assignment10/assets/143886913/da1ea832-80b8-4b21-8469-106a44b83063)  
  

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

![image](https://github.com/mkdk72ki/assignment10/assets/143886913/2cdcf912-ccce-443d-a2ab-a61c76d396ed)  
![patchOk](https://github.com/mkdk72ki/assignment10/assets/143886913/94023e17-0e6f-4335-ab1c-af4e90b45070)  
![patchOkDb](https://github.com/mkdk72ki/assignment10/assets/143886913/71775836-84fc-4e51-9962-20c1144a1b2b)  
  
## DELETE処理
DELETEリクエスト`http://localhost:8080/user/{id}`を実行すると、指定したIDに紐づいたレコードを削除し、メッセージをJSON形式で返す。
  
![delete前](https://github.com/mkdk72ki/assignment10/assets/143886913/8e407e5b-5560-4b40-9de0-0d3f5093394d)  
![deleteOk](https://github.com/mkdk72ki/assignment10/assets/143886913/79efd993-4aa6-4796-b783-79a63a1b08b6)  
![deleteOkDb](https://github.com/mkdk72ki/assignment10/assets/143886913/fa102c02-71df-460a-b4ae-7ad782d411a1)  
