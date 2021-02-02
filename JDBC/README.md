## JDBC
java, database, api

<br>

---

- 1. JDBC Driver 설치

- 2.
select
```java
import java.sql.*

// ResultSet
// executeQuery()
// getString()
void select(){
    // database마다 상이
    // 1. 연결할 dbms 선택
    String url = "jdbc:oracle:thin:@xxx.xxx.xxx.xx:xxxx:xx";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rset = null;

    try{
        // 2. Driver 등록
        Class.forName("oracle.jdbc.driver.OracleDriver")

        // 3. DBMS 연결
        conn = DriverManager.getConnection(url,"id", "passwd");

        // 4. Statement 생성
        stmt = conn.createStatement();

        // 5. 쿼리 작성
        String query = "SELECT *" + " FROM ~~~";

        // 6. 쿼리 전송
        rset = stmt.executeQuery(query);

        while(rset.next()){
            print(rset.getString(1), rset.getString(2), ...)
        }

        // ...

    }catch(Exception e){

    }finally{
        try{
            // 역으로 닫기
            rset.close();
            stmt.close();
            conn.close();
        }catch(Exception e){
        }
    }
}
```

<br>

DML  
insert, delete, update
```java
import java.sql.*

// executeUpdate()
void select(){
    // database마다 상이
    // 1. 연결할 dbms 선택
    String url = "jdbc:oracle:thin:@xxx.xxx.xxx.xx:xxxx:xx";

    Connection conn = null;
    Statement stmt = null;
    // ResultSet rset = null;

    try{
        // 2. Driver 등록
        Class.forName("oracle.jdbc.driver.OracleDriver")

        // 3. DBMS 연결
        conn = DriverManager.getConnection(url,"id", "passwd");

        // 3-1. 트랜잭션 적용
        conn.setAutoCommit(false);

        // 4. Statement 생성
        stmt = conn.createStatement();

        // 5. 쿼리 작성
        String query = "DELETE ~~~";

        // 6. 쿼리 전송
        // 변경된 데이터 개수 반환
        int result = stmt.executeUpdate(query);

        // ...

        if(result > 0){
            // 변경 성공, 트랜잭션 완료
            conn.commit();
        }else{
            // 변경 없음
            conn.rollback();
            // or do nothing
        }

    }catch(Exception e){
        // 예외 발생
        print(e.getMessage());
        // 롤백
        conn.rollback();
    }finally{
        try{
            // 역으로 닫기
            stmt.close();
            conn.close();
        }catch(Exception e){
        }
    }
}
```

<br>

PreparedStatement
동적 쿼리
```java
import java.sql.*

// PreparedStatement
// prepareStatement()
// setString()
void select(){
    // database마다 상이
    // 1. 연결할 dbms 선택
    String url = "jdbc:oracle:thin:@xxx.xxx.xxx.xx:xxxx:xx";

    Connection conn = null;
    //Statement stmt = null;
    PreparedStatement stmt = null;

    try{
        // 2. Driver 등록
        Class.forName("oracle.jdbc.driver.OracleDriver")

        // 3. DBMS 연결
        conn = DriverManager.getConnection(url,"id", "passwd");

        // 3-1. 트랜잭션 적용
        conn.setAutoCommit(false);

        
        // 4. 쿼리 작성
        String query = "update table"
                        + " set id = ?"
                        + " where id = ?";

        // 5. PreparedStatement 생성
        // stmt = conn.createStatement();
        stmt = conn.prepareStatement(query);

        // 5-1. prepareStatement()
        stmt.setString(1, "something");
        stmt.setString(2, "something");

        // 6. 쿼리 전송
        // 인자 없이 stmt 함수 실행
        // int result = stmt.executeUpdate(query);
        int result = stmt.executeUpdate();

        // ...

        if(result > 0){
            // 변경 성공, 트랜잭션 완료
            conn.commit();
        }else{
            // 변경 없음
            conn.rollback();
            // or do nothing
        }

    }catch(Exception e){
        // 예외 발생
        print(e.getMessage());
        // 롤백
        conn.rollback();
    }finally{
        try{
            // 역으로 닫기
            stmt.close();
            conn.close();
        }catch(Exception e){
        }
    }
}
```