/**
 * Created by Misaka on 2016-06-01.
 */
class WriteMode extends LayoutComponent {
    constructor(rendererClass, rendererFunction) {
        super(rendererClass, rendererFunction);
        this._title = '';
        this._description = '';
        this._tags = '';
    }

    set title(title) {
        this._title = title;
    }

    get title() {
        return this._title;
    }

    set description(description) {
        this._description = description;
    }

    get description() {
        return this._description;
    }

    set tags(tags) {
        this._tags = tags;
    }

    get tags() {
        return this._tags;
    }

    createParameter() {
        return 'uploadCategoryCode=' + this.categoryCode + '&uploadTitle=' + this.title + '&uploadDescription=' + this.description + '&uploadTags=' + this.tags;
    }

    upload() {
        if(document.getElementById('preview').getElementsByTagName('video')[0].getAttribute('src') == null) {
            alert('Checked Upload File !!');

        } else {
            this.title = document.getElementById('videoTitle').value;
            this.description = document.getElementById('videoDescription').value;
            this.tags = document.getElementById('videoTags').value;

            let renderer = new BlogModeRenderer();

            this.rendererClass = renderer
            this.rendererFunction = renderer.renderUploadCallback;
            this.type = 'POST';
            this.uri = '/contents/upload';
            this.parameter = this.createParameter();
            this.file = document.getElementById('uploadFile').files[0];
            this.callRest(this);
        }
    }

    preview(fileElement) {
        // html5
        let url = window.URL || window.webkitURL;
        let file = fileElement.files[0]
        let fileURL = url.createObjectURL(file);
        document.getElementById('preview').getElementsByTagName('video')[0].setAttribute('controls', '');
        document.getElementById('preview').getElementsByTagName('video')[0].setAttribute('src', fileURL);
    }
}