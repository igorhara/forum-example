import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  loginForm:FormGroup;
  constructor(private fb:FormBuilder,private auth:AuthService,private router:Router) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      login: [,Validators.required],
      password:[,Validators.required]
    });
  }


  onSubmit(){
    var login:any = this.loginForm.value;
    this.auth.login(login).subscribe(p=>this.router.navigate(['/']));
  }

}
