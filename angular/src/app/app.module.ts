import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { PostListComponent } from './post-list/post-list.component';
import { PostItemComponent } from './post-list/post-item/post-item.component';
import {PostService} from "./service/post.service";
import { HeaderComponent } from './header/header.component';
import { PostFormComponent } from './post-form/post-form.component';
import {ReactiveFormsModule} from "@angular/forms";
import {AppRoutingModule} from "./app-routing.module";
import { LoginComponent } from './login/login.component';
import {AuthService} from "./service/auth.service";
import { PostDetailComponent } from './post-detail/post-detail.component';
import {HttpErrorInterceptor} from "./service/http-error.interceptor";
import { CommentItemComponent } from './post-detail/comment-item/comment-item.component';
import { CommentFormComponent } from './comment-form/comment-form.component';


@NgModule({
  declarations: [
    AppComponent,
    PostListComponent,
    PostItemComponent,
    HeaderComponent,
    PostFormComponent,
    LoginComponent,
    PostDetailComponent,
    CommentItemComponent,
    CommentFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [
    PostService,
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
