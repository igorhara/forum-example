import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { PostListComponent } from './post-list/post-list.component';
import { PostItemComponent } from './post-list/post-item/post-item.component';
import {PostService} from "./service/post.service";
import { HeaderComponent } from './header/header.component';
import { PostFormComponent } from './post-form/post-form.component';
import {ReactiveFormsModule} from "@angular/forms";
import {AppRoutingModule} from "./app-routing.module";
import { LoginComponent } from './login/login.component';
import {AuthService} from "./service/auth.service";


@NgModule({
  declarations: [
    AppComponent,
    PostListComponent,
    PostItemComponent,
    HeaderComponent,
    PostFormComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [
    PostService,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
