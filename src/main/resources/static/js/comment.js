document.addEventListener('DOMContentLoaded', function() {
    const addCommentBtn = document.getElementById('add-comment-btn');
    const commentForm = document.getElementById('comment-form');
    const commentModal = document.getElementById('comment-modal');
    const closeModalBtn = document.querySelector('.close-modal');

    if (addCommentBtn && commentModal) {
        addCommentBtn.addEventListener('click', () => {
            commentModal.style.display = 'flex';
        });
    }

    if (closeModalBtn && commentModal) {
        closeModalBtn.addEventListener('click', () => {
            commentModal.style.display = 'none';
        });
    }

    if (commentForm) {
        commentForm.addEventListener('submit', function(e) {
            e.preventDefault();

            const data = {
                userId: document.getElementById('user-id').value,
                postId: document.getElementById('post-id').value,
                content: document.getElementById('comment-content').value
            };

            fetch('/api/v1/comments', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            }).then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    alert('Failed to create Comment');
                }
            }).catch(error => {
                console.error('Error:', error);
                alert('An error occurred while creating the comment');
            });
        });
    }
});