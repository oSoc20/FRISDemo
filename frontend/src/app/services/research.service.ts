import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Research } from '../models/research.model';
import { Project } from '../models/project.model';
import { Publication } from '../models/publication.model';

@Injectable({
  providedIn: 'root'
})
export class ResearchService {
  baseURL = environment.baseurl;

  constructor(
    private http: HttpClient) {
  }

  // getResearch() {
  //   // this.http.get<any>(this.baseURL + '/research').subscribe(result => {
  //   //   console.log(result);
  //   //   let projects: Project[];
  //   //   let publications: Publication[];
  //   //   result.projects.forEach(p => {
  //   //     // tslint:disable-next-line: max-line-length
  //   //     let project = new Project(p.id, p.title.englishTitle, p.title.dutchTitle, p.englishKeywords, p.dutchKeywords,p.abstract.englishAbstract, p.abstract.dutchAbstract);
  //   //     projects.push(project);
  //   //   });
  //   //   result.publications.forEach(p => {
  //   //     let publication = new Publication(p.uuid, p.titleEn, p.titleNl,p.keywordsEn, p.keywordsNl, p.abstractEn, p.abstractNl, p.doi);
  //   //     publications.push(publication);
  //   //   });
  //   //   let research = new Research(projects, publications);
  //   // });
  //   return this.http.get<any>(this.baseURL + '/research');
  // }

  getResearch(): Promise<Research> {
    return new Promise((resolve, reject) => {
      this.http.get<any>(this.baseURL + '/research').subscribe(result => {
        console.log(result);
        let projects: Project[] = [];
        let publications: Publication[] = [];
        result.projects.forEach(p => {
          // tslint:disable-next-line: max-line-length
          let project = new Project(p.id, p.title.englishTitle, p.title.dutchTitle, p.englishKeywords, p.dutchKeywords, p.abstract.englishAbstract, p.abstract.dutchAbstract);
          projects.push(project);
        });
        result.publications.forEach(p => {
          let publication = new Publication(p.uuid, p.titleEn, p.titleNl, p.keywordsEn, p.keywordsNl, p.abstractEn, p.abstractNl, p.doi);
          publications.push(publication);
        });
        console.log(projects)
        resolve(new Research(projects, publications));
      });
    });
  }

  enrichProject(project) {

  }

  enrichPublication(publication) {

  }
}
