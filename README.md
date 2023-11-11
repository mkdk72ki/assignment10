# assginment10
7月度、旧第10回の課題です。  
前回のプロジェクトを一部改変したものになります。
  
# 動作確認
  
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

