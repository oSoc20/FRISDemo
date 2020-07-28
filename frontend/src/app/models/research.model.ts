import { Project } from './project.model';
import { Publication } from './publication.model';

export class Research {
  constructor(
    public projects: Project[],
    public publications: Publication[]) {
  }
}
