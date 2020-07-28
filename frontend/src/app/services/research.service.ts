import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ResearchService {
  baseURL = environment.baseurl;

  constructor(
    private http: HttpClient) {
  }

  getResearch() {
    return this.http.get<JSON>(this.baseURL + '/research');
  }

}
