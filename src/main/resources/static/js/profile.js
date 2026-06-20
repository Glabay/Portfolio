document.addEventListener('DOMContentLoaded', function() {
    const editBioBtn = document.getElementById('edit-bio-btn');
    const bioForm = document.getElementById('bio-form');
    const bioModal = document.getElementById('bio-modal');
    const closeBioModalBtns = document.querySelectorAll('.close-bio-modal');

    if (editBioBtn && bioModal) {
        editBioBtn.addEventListener('click', () => {
            bioModal.style.display = 'flex';
        });
    }

    closeBioModalBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            if (bioModal) {
                bioModal.style.display = 'none';
            }
        });
    });

    if (bioForm) {
        bioForm.addEventListener('submit', function(e) {
            e.preventDefault();

            const profileId = document.getElementById('profile-id').value;
            const data = {
                bio: document.getElementById('bio-input').value
            };

            fetch(`/api/v1/profiles/${profileId}/bio`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            }).then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    alert('Failed to update Bio');
                }
            }).catch(error => {
                console.error('Error:', error);
                alert('An error occurred while updating the Bio');
            });
        });
    }
});
