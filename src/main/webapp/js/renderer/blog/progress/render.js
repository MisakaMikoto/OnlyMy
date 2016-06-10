/**
 * Created by Misaka on 2016-06-09.
 */
class BlogUploadProgressRenderer {
    constructor() {
    }

    renderInitCompleted() {
        alert("업로드 준비가 완료되었습니다. 조금 기다려 주세요.");
    }

    renderProgress(message) {
        alert(message);
    }

    renderUploadCompleted() {
        alert("업로드가 완료되었습니다.");

        let renderer = new BlogLayoutRenderer();
        let category = new Category(renderer, renderer.renderCategoryList);
        category.refresh();
    }
}