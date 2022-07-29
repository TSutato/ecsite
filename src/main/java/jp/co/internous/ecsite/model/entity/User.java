/*DBテーブルの1レコードを表すユーザーテーブルのEntityクラスを格納。*/
package jp.co.internous.ecsite.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/*クラス宣言部を追加。*/
/*Entityアノテーションを付与すると、Springの機能による当該クラスはEntityとして振る舞う。*/
/*Tableアノテーションは、DBにある「どのテーブルの実体なのか」を指定する。*/
@Entity
@Table(name="user")
public class User {

	@Id //プライマリキーであることを指定します。
	@Column(name="id") //テーブルのどのカラムをマッピングするかを指定します。
	@GeneratedValue(strategy=GenerationType. IDENTITY) //IDフィールドの振る舞い方を指定。※今回はAuto_incrementとして振る舞う。
	private long id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	 private String password;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="is_admin")
	private int isAdmin;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public int getIsAdmin() {
		return isAdmin;
	}
	
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
}
