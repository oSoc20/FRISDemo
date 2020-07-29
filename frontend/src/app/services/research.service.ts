import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Research } from '../models/research.model';
import { Project } from '../models/project.model';
import { Publication } from '../models/publication.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResearchService {
  baseurlDemo = environment.baseurlDemo;
  baseurlEnricher = environment.baseurl;
  private jsonURL = 'assets/data.json';

  constructor(private http: HttpClient) {
  }
  public getResearchFromFile(): Observable<any> {
    return this.http.get(this.jsonURL);
  }

  getResearch(): Promise<Research> {
    return new Promise((resolve, reject) => {
      this.http.get<any>(this.baseurlDemo + '/research').subscribe(result => {
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
        console.log(projects);
        console.log(JSON.stringify(new Research(projects, publications)));
        resolve(new Research(projects, publications));
      });
    });
  }

  enrichProject(project) {
    return this.http.post<any>(this.baseurlEnricher + '/api/projects/enrich', project);
  }

  enrichPublication(publication) {
    return this.http.post<any>(this.baseurlEnricher + '/api/publications/enrich', publication);
  }
}
