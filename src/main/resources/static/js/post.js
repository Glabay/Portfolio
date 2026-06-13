document.addEventListener('DOMContentLoaded', function() {
    const addPostBtn = document.getElementById('add-post-btn');
    const postForm = document.getElementById('post-form');
    const postModal = document.getElementById('post-modal');
    const closeModalBtn = document.querySelector('.close-modal');

    if (addPostBtn && postModal) {
        addPostBtn.addEventListener('click', () => {
            postModal.style.display = 'flex';
        });
    }

    if (closeModalBtn && postModal) {
        closeModalBtn.addEventListener('click', () => {
            postModal.style.display = 'none';
        });
    }

    if (postForm) {
        postForm.addEventListener('submit', function(e) {
            e.preventDefault();

            const data = {
                title: document.getElementById('post-title').value,
                content: document.getElementById('post-content').value
            };

            fetch('/blogs/post', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            }).then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    alert('Failed to create Post');
                }
            }).catch(error => {
                console.error('Error:', error);
                alert('An error occurred while creating the Post');
            });
        });
    }
});