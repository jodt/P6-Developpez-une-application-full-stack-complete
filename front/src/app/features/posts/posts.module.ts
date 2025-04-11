import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PostsRoutingModule } from './posts-routing.module';
import { PostsComponent } from './components/posts/posts.component';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatButtonModule } from '@angular/material/button';
import { DetailComponent } from './components/detail/detail.component';
import { MatFormFieldModule } from '@angular/material/form-field';


@NgModule({
  declarations: [
    PostsComponent,
    DetailComponent
  ],
  imports: [
    CommonModule,
    PostsRoutingModule,
    MatCardModule,
    MatGridListModule,
    MatButtonModule,
    MatFormFieldModule
  ]
})
export class PostsModule { }
