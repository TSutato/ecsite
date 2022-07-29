package jp.co.internous.ecsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.ecsite.model.dao.GoodsRepository;
import jp.co.internous.ecsite.model.dao.UserRepository;
import jp.co.internous.ecsite.model.entity.Goods;
import jp.co.internous.ecsite.model.entity.User;
import jp.co.internous.ecsite.model.form.GoodsForm;
import jp.co.internous.ecsite.model.form.LoginForm;

@Controller
/*URL(localhost:8080/ecsite/admin/)でアクセスできるよう設定する。*/
@RequestMapping("/ecsite/admin")
public class AdminController {
      /*User,GoodsのRepositoryを読み込み。*/
	  @Autowired
	  private UserRepository userRepos;
	  
	  @Autowired
	  private GoodsRepository goodsRepos;
	
	  @RequestMapping("/")
	  public String index() {
	  /*トップページ(adminindex.html)に遷移するメソッド*/
		  return "adminindex";
	  }
	  /*ユーザー名とパスワードをLoginFormを介して受取、コンソールに表示。*/
	  @PostMapping("/welcome")
	  public String welcome(LoginForm form, Model m) {
		  /*ユーザー名とパスワードでユーザーを詮索。*/
		  List<User> users = userRepos.findByUserNameAndPassword(form.getUserName(),form.getPassword());
		  System.out.println(form.getUserName() + "：" + form.getPassword()); 
		  if (users != null && users.size() > 0) {
			  boolean isAdmin = users.get(0).getIsAdmin() != 0;
			  System.out.println(users.get(0).getIsAdmin() + "：" + isAdmin);
			  if (isAdmin) {
				  List<Goods> goods = goodsRepos.findAll();
				  m.addAttribute("userName", users.get(0).getUserName());
				  m.addAttribute("password", users.get(0).getPassword());
				  m.addAttribute("goods",goods);
			  }
		  }
		   return "welcome";
	  }
	  /*新規商品の登録機能*/
	  @RequestMapping("/goodsMst")
	  public String goodsMst(LoginForm form, Model m) {
		  m.addAttribute("userName", form.getUserName());
		  m.addAttribute("password", form.getPassword());
		  
		  return "goodsmst";
	  }
	  
	  @RequestMapping("/addGoods")
	  public String addGoods(GoodsForm goodsForm, LoginForm loginForm, Model m) {
		  m.addAttribute("userName", loginForm.getUserName());
		  m.addAttribute("password", loginForm.getPassword());
		  
		  Goods  goods = new Goods();
		  goods.setGoodsName(goodsForm.getGoodsName());
		  goods.setPrice(goodsForm.getPrice());
		  goodsRepos.saveAndFlush(goods);
	  
	  return "forward:/ecsite/admin/welcome";
	  }
	  
	  /*新規商品の削除機能*/
	  /*ajaxを使用した方式での処理(REST)*/
	 
	  @ResponseBody
	  @PostMapping("/api/deleteGoods")
	  public String deleteApi(@RequestBody GoodsForm f, Model m) {
		  try {
			  goodsRepos.deleteById(f.getId());
		  } catch (IllegalArgumentException e) {
			  return "-1";
		  }
		   return "1";
	  }
	 
}
