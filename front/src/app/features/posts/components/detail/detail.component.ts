import { Component, OnInit } from '@angular/core';
import { map, Observable, of } from 'rxjs';
import { Post } from '../../interfaces/post.interface';
import { ActivatedRoute, ActivatedRouteSnapshot } from '@angular/router';
import { Comment } from '../../interfaces/comment.interface';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrl: './detail.component.css',
})
export class DetailComponent implements OnInit {
  post$!: Observable<Post>;
  comments$!: Observable<Comment[]>;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.post$ = this.route.data.pipe(map((data) => data['post']));
  }
}
