document.addEventListener('DOMContentLoaded', function() {
    const addProjectBtn = document.getElementById('add-project-btn');
    const projectForm = document.getElementById('project-form');
    const projectModal = document.getElementById('project-modal');
    const closeModalBtns = document.querySelectorAll('.close-modal');

    if (addProjectBtn && projectModal) {
        addProjectBtn.addEventListener('click', () => {
            projectModal.style.display = 'flex';
        });
    }

    closeModalBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            if (projectModal) {
                projectModal.style.display = 'none';
            }
        });
    });

    if (projectForm) {
        projectForm.addEventListener('submit', function(e) {
            e.preventDefault();

            const data = {
                name: document.getElementById('project-name').value,
                repositoryUrl: document.getElementById('project-repo-url').value,
                synopsis: document.getElementById('project-synopsis').value
            };

            fetch('/projects', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            }).then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    alert('Failed to add Project');
                }
            }).catch(error => {
                console.error('Error:', error);
                alert('An error occurred while adding the Project');
            });
        });
    }
});
