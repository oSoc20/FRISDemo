export class Publication {
  constructor(
    public uuid: string,
    public titleEn: string,
    public titleNl: string,
    public keywordsEn: string[],
    public keywordsNl: string[],
    public abstractEn: string,
    public abstractNl: string,
    public doi: string
  ) {
  }
}
